<template>
      <Breadcrumb/>
  <div class="container">
      <h1 class="display-6">{{test.title}}</h1>
      <button class="btn btn-primary" v-if="flag" @click="toggle">Begin Test</button>
      <div v-else>
        <div>Speaking Prompt: {{test.prompt}}</div>
        <hr />
        <div v-if="finished">Finish.</div>
        <div v-else>
          <div v-if="recording">Recording ...</div>
          <div v-else>Record start in {{countDown}} seconds.</div>
        </div>
        <hr />
        <button class="btn btn-primary" id="stop" @click="stop" disabled>Stop</button>
        <button class="btn btn-primary" id="play" @click="playback" disabled>Play</button>
        <a id="download" disabled>Download</a>
      </div>
  </div>
</template>

<script>
import Breadcrumb from '../components/Breadcrumb.vue'
// @ is an alias to /src

export default {
  components: { Breadcrumb },
  name: 'AudioTestTaking',
  data: function () {
    return {
        finished: false,
        recording: false,
        flag: true,
        mediaRecorder:``,
        audio:``,
        countDown: null,
        test:{}
    }
  },
  created() {
    this.setHeader()
        this.init()
        this.getTest(this.$route.params.id)
  },
  methods: {
        async getTest(id) {
            const { data: res } = await this.$http.get(`/audioTests/findOne/${id}`)
            this.test = res.data
            this.countDown = this.test.prepTime
            console.log(this.test)
        },


        init: function(){
          navigator.mediaDevices.getUserMedia({ audio: true })
          .then(stream => {
            this.mediaRecorder = new MediaRecorder(stream);

          });
        },

        record: function(){
          this.mediaRecorder.start();

          const audioChunks = [];
          this.mediaRecorder.addEventListener("dataavailable", event => {
            audioChunks.push(event.data);
          });

          var http = this.$http
          var user = this.$store.state.auth.user;
          var testId = this.test.id

          this.mediaRecorder.addEventListener("stop", () => {

              const audioBlob = new Blob(audioChunks);
              const audioUrl = URL.createObjectURL(audioBlob);
              this.audio = new Audio(audioUrl);
              this.audio.play();

              var reader = new FileReader();
              var base64data;
              reader.readAsDataURL(audioBlob); 
              reader.onloadend = function() {
                base64data = reader.result;                
                console.log(base64data);
                

                var data = [user.username,testId,base64data]
                var chapter = {
                 text: data,
                }
                http.post('/storage/uploadAudioAnswer', chapter)
              }
              
              const a = document.getElementById("download");
              a.href = audioUrl;
              // the filename you want
              a.download = 'answer.wav';
            });
        },

        playback: function(){
          this.audio.play()
        },
        
        stop: function() {
            this.mediaRecorder.stop()
            this.finished = true
            const stopB = document.getElementById("stop");
            stopB.disabled = true;
            const playB = document.getElementById("play");
            playB.disabled = false;
        },


        countDownTimer() {
          if(this.countDown > 0) {
            setTimeout(() => {
              this.countDown -= 1
              this.countDownTimer()
            }, 1000)
          }
          if (this.countDown == 0) {
            this.recording = true
            this.record()
            const stopB = document.getElementById("stop");
            stopB.disabled = false;
          }
        },

        toggle(){
          this.flag = !this.flag
          this.countDownTimer()
        },
            setHeader(){
      var current = new Date().getTime() / 1000
      if (current > this.$store.state.auth.expired){
        this.$store.dispatch('auth/logout')
        this.$router.push('/login');
      }
      else this.$http.defaults.headers.common['Authorization'] = this.$store.state.auth.token; 
    },
  }
}
</script>

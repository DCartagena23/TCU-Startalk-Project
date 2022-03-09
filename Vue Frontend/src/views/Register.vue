<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="../assets/small_logo.png"
      />
      <Form name="form" @submit="handleRegister">
        <div v-if="!successful">
          <div class="form-group">
            <label for="username">Username</label>
            <input 
            name="field"
            type="text"
            class="form-control"
            v-model="username"
            :rules="isRequired"
                /> 
            <!-- <div
              v-if="submitted && errors.has('username')"
              class="alert-danger"
            >{{errors.first('username')}}</div> -->
          </div>
          <div class="form-group">
            <label for="email">Email</label>
              <input
              v-model="email"
              type="email"
              class="form-control"
              name="email"
              :rules="isRequired"
            />
            <!-- <div
              v-if="submitted && errors.has('email')"
              class="alert-danger"
            >{{errors.first('email')}}</div>
          </div> -->
          <div class="form-group">
            <label for="password">Password</label>
              <input
              v-model="password"
              type="password" 
              class="form-control"
              name="password"
              :rules="isRequired"
            />
            <!-- <div
              v-if="submitted && errors.has('password')"
              class="alert-danger"
            >{{errors.first('password')}}</div> -->
          </div>

          <div class="form-group">
            <label for="teacher">Teacher</label>
              <input
              v-model="teacher"
              type="checkbox"
            />
            <!-- <div
              v-if="submitted && errors.has('password')"
              class="alert-danger"
            >{{errors.first('password')}}</div> -->
          </div>

          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block">Sign Up</button>
          </div>
        </div>
      </Form>
      <ErrorMessage name="field" />
  </div>
  </div>
</template>
<script>
import { Form, ErrorMessage } from 'vee-validate';
// const submitted = false;
// const successful = false;
// const message = 'Error';
// const user = new User('','','')
export default {
  name: 'Register',
  data() {
    return {
      username:"",
      email:"",
      password:"",
      teacher:false,
      submitted: false,
      successful: false,
      message: ''
    };
  },
  components: {
    Form,ErrorMessage
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/home');
    }
  },
  methods: {
    handleRegister() {
      this.message = '';
      this.submitted = true;
      var roles = []
      if(this.teacher) roles = ["teacher"]
      else roles = ["student"]
      var user ={
        username: this.username,
        password: this.password,
        email: this.email,
        roles: roles
      }
      console.log(user)
          this.$store.dispatch('auth/register', user).then(
            (data) => {
              this.message = data.message;
              this.successful = true;
              this.$router.push('/login');
            },
            (error) => {
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
              this.successful = false;
            }
          );
    },
    isRequired(value) {
      if (value && value.trim()) {
        return true;
      }
      return 'This is required';
    },
  },
};
</script>
<style scoped>
label {
  display: block;
  margin-top: 10px;
}
.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}
.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>
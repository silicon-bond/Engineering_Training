// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ELementUI from 'element-ui'
import axios from 'axios'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(ELementUI);
Vue.config.productionTip = false
Vue.prototype.$axios = axios



axios.interceptors.request.use((request) => {
    if (localStorage.getItem('token')) {
        request.headers.common = {
            'Authorization': localStorage.getItem('token') ? localStorage.getItem('token') : router.push({ path: '/login' }),
        };
    }
    return request;
});

axios.interceptors.response.use(function (response) {
  if (response.data.code === '401' && response.data.message !== '账号或密码错误！') {
    localStorage.clear();
    router.push('/login');
  } else {
    return response;
  }
});

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    axios,
    components: { App },
    template: '<App/>'
})

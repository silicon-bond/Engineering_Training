import Vue from 'vue'
import Router from 'vue-router'

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

//登录
const Register = () => import('../components/Login/Register')
const Login = () => import('../components/Login/Login')
const LoginBox = () => import('../components/Login/LoginBox')
const ForgetPassword = () => import('../components/Login/ForgetPassword')
//用户端
const UserMain = () => import('../components/User/Main')
const UserLogisticsList = () => import('../components/User/LogisticsList')
const UserMail = () => import('../components/User/Mail')
const UserAdvice = () => import('../components/User/Advice')
const UserPersonalCenter = () => import('../components/User/PersonalCenter')
//快递员端
const DeliverymanMain = () => import('../components/Deliveryman/Main')
const DeliverymanLogisticsList = () => import('../components/Deliveryman/LogisticsList')
const DeliverymanAdvice = () => import('../components/Deliveryman/Advice')
const DeliverymanPersonalCenter = () => import('../components/Deliveryman/PersonalCenter')
const DeliverymanReceivedLogisticsList = () => import('../components/Deliveryman/ReceivedLogisticsList')
//网点管理员端
const BranchMain = () => import('../components/BranchAdministrator/Main')
const BranchAdviceList = () => import('../components/BranchAdministrator/AdviceList')
const BranchDeliverymanList = () => import('../components/BranchAdministrator/DeliverymanList')
const BranchLogisticsList = () => import('../components/BranchAdministrator/LogisticsList')
const BranchPersonalCenter = () => import('../components/BranchAdministrator/PersonalCenter')
//系统管理员端
const SystemMain = () => import('../components/SystemAdministrator/Main')
const SystemAdviceList = () => import('../components/SystemAdministrator/AdviceList')
const SystemBranchList = () => import('../components/SystemAdministrator/BranchList')
const SystemLogisticsList = () => import('../components/SystemAdministrator/LogisticsList')
const UserList = () => import('../components/SystemAdministrator/UserList')
const DeliverymanList = () => import('../components/SystemAdministrator/DeliverymanList')
const BranchAdminList = () => import('../components/SystemAdministrator/BranchAdminList')



//1.安装插件
Vue.use(Router)


const routes = [
  {
    path: '',
    redirect :'/login'
  },
  {//注册登录
    path: '/login',
    component: LoginBox,
    children: [
      {
        path: '',
        redirect: '/login/login'
      },
      {
        path: '/login/login',
        component:Login
      },
      {
        path: '/login/register',
        component:Register
      },
      {
        path: '/login/forgetPassword',
        component:ForgetPassword
      },
    ]
  },
  {//用户端
    path: '/user',
    component: UserMain,
    children: [
      {
        path: '',
        redirect :'/user/logistics'
      },

      {
        path: '/user/logistics',
        component:UserLogisticsList
      },
      {
        path: '/user/mail',
        component:UserMail
      },
      {
        path: '/user/advice',
        component:UserAdvice
      },
      {
        path: '/user/personalCenter',
        component:UserPersonalCenter
      }
    ]
  },
  {//快递员端
    path: '/deliveryman',
    component: DeliverymanMain,
    children: [
      {
        path: '',
        redirect :'/deliveryman/logistics'
      },
      {
        path: '/deliveryman/logistics',
        component:DeliverymanLogisticsList
      },
      {
        path: '/deliveryman/advice',
        component:DeliverymanAdvice
      },
      {
        path: '/deliveryman/personalCenter',
        component:DeliverymanPersonalCenter
      },
      {
        path: '/deliveryman/receivedLogistics',
        component:DeliverymanReceivedLogisticsList
      }
    ]
  },
  {//网点管理员端
    path: '/branch',
    component: BranchMain,
    children: [
      {
        path: '',
        redirect :'/branch/logistics'
      },
      {
        path: '/branch/logistics',
        component:BranchLogisticsList
      },
      {
        path: '/branch/deliverymanList',
        component:BranchDeliverymanList
      },
      {
        path: '/branch/adviceList',
        component:BranchAdviceList
      },
      {
        path: '/branch/personalCenter',
        component:BranchPersonalCenter
      }
    ]
  },
  {//系统管理员端
    path: '/system',
    component: SystemMain,
    children: [
      {
        path: '',
        redirect :'/system/logistics'
      },
      {
        path: '/system/logistics',
        component:SystemLogisticsList
      },
      {
        path: '/system/branchList',
        component:SystemBranchList
      },
      {
        path: '/system/adviceList',
        component:SystemAdviceList
      },
      {
        path: '/system/userList',
        component:UserList
      },
      {
        path: '/system/deliverymanList',
        component:DeliverymanList
      },
      {
        path: '/system/branchAdminList',
        component:BranchAdminList
      }
    ]
  }
]

//2.创建Router对象,export default导出
export default new Router({
    //配置路由和组件之间的关系，
    routes,
    mode: "history"
})



//3.在vue里挂载

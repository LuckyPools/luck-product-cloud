import Home from "@/views/home/index.vue";
import Login from "@/views/login/index.vue";
import Test from "@/views/test/index.vue";


export const customRoutes = [
    {
        name: 'home',
        path: '/home',
        component: Home,
        meta: {
            title: 'home',
            i18nKey: 'route.exception',
            icon: 'ant-design:exception-outlined',
            order: 7
        },
        children: [
            {
                name: 'manage_menu',
                path: '/manage/menu',
                component: Login,
                meta: {
                    title: 'manage_menu',
                    i18nKey: 'route.manage_menu',
                    icon: 'material-symbols:route',
                    order: 3,
                    keepAlive: true
                }
            },
            {
                name: 'manage_role',
                path: '/manage/role',
                component: Test,
                meta: {
                    title: 'manage_role',
                    i18nKey: 'route.manage_role',
                    icon: 'carbon:user-role',
                    order: 2
                }
            }
        ]
    }
];

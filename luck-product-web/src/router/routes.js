import BaseLayout from "@/layout/base-layout/index.vue";


export const customRoutes = [
    {
        name: 'home',
        path: '/home',
        component: BaseLayout,
        meta: {
            title: 'home',
            i18nKey: 'route.exception',
            icon: 'ant-design:exception-outlined',
            order: 7
        }
    }
];

import BaseLayout from "@/layout/base-layout/index.vue";


export const layouts = {
    base: BaseLayout,
};

export const views = {
    login: () => import("@/views/login/index.vue"),
    test: () => import("@/views/test/index.vue"),
    404: () => import("@/views/test/index.vue"),
    home: () => import("@/views/home/index.vue")
}

export const customRoutes = [
    {
        name: 'home',
        path: '/home',
        component: 'layout.base$view.home',
        meta: {
            title: 'home',
            i18nKey: 'route.home',
            icon: 'mdi:monitor-dashboard',
            order: 1
        }
    },
    {
        name: 'fire',
        path: '/fire',
        component: 'layout.base',
        meta: {
            title: 'fire',
            i18nKey: 'route.document',
            icon: 'mdi:monitor-dashboard',
            order: 1
        },
        children: [
            {
                name: 'login_page',
                path: '/login',
                component: 'view.login',
                meta: {
                    title: 'manage_menu',
                    i18nKey: 'route.manage_menu',
                    icon: 'material-symbols:route',
                    order: 3,
                    keepAlive: true
                }
            },
            {
                name: 'test_page',
                path: '/test',
                component: 'view.test',
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

export const ROOT_ROUTE = {
    name: 'root',
    path: '/',
    redirect: '/home',
    meta: {
        title: 'root',
        constant: true
    }
};

const NOT_FOUND_ROUTE = {
    name: 'not-found',
    path: '/:pathMatch(.*)*',
    component: 'layout.base$view.404',
    meta: {
        title: 'not-found',
        constant: true
    }
};

const builtinRoutes = [ROOT_ROUTE, NOT_FOUND_ROUTE];


export function transformElegantRoutesToVueRoutes(
    routes,
    layouts,
    views
) {
    return routes.flatMap(route => transformElegantRouteToVueRoute(route, layouts, views));
}


export function transformElegantRouteToVueRoute(
    route,
    layouts,
    views
) {
    const LAYOUT_PREFIX = 'layout.';
    const VIEW_PREFIX = 'view.';
    const ROUTE_DEGREE_SPLITTER = '_';
    const FIRST_LEVEL_ROUTE_COMPONENT_SPLIT = '$';

    function isLayout(component) {
        return component.startsWith(LAYOUT_PREFIX);
    }

    function getLayoutName(component) {
        const layout = component.replace(LAYOUT_PREFIX, '');

        if(!layouts[layout]) {
            throw new Error(`Layout component "${layout}" not found`);
        }

        return layout;
    }

    function isView(component) {
        return component.startsWith(VIEW_PREFIX);
    }

    function getViewName(component) {
        const view = component.replace(VIEW_PREFIX, '');

        if(!views[view]) {
            throw new Error(`View component "${view}" not found`);
        }

        return view;
    }

    function isFirstLevelRoute(item) {
        return !item.name.includes(ROUTE_DEGREE_SPLITTER);
    }

    function isSingleLevelRoute(item) {
        return isFirstLevelRoute(item) && !item.children?.length;
    }

    function getSingleLevelRouteComponent(component) {
        const [layout, view] = component.split(FIRST_LEVEL_ROUTE_COMPONENT_SPLIT);

        return {
            layout: getLayoutName(layout),
            view: getViewName(view)
        };
    }

    const vueRoutes = [];

    // add props: true to route
    if (route.path.includes(':') && !route.props) {
        route.props = true;
    }

    const { name, path, component, children, ...rest } = route;

    const vueRoute = { name, path, ...rest } ;

    try {
        if (component) {
            if (isSingleLevelRoute(route)) {
                const { layout, view } = getSingleLevelRouteComponent(component);

                const singleLevelRoute = {
                    path,
                    component: layouts[layout],
                    children: [
                        {
                            name,
                            path: '',
                            component: views[view],
                            ...rest
                        }
                    ]
                };

                return [singleLevelRoute];
            }

            if (isLayout(component)) {
                const layoutName = getLayoutName(component);

                vueRoute.component = layouts[layoutName];
            }

            if (isView(component)) {
                const viewName = getViewName(component);

                vueRoute.component = views[viewName];
            }

        }
    } catch (error) {
        console.error(`Error transforming route "${route.name}": ${error.toString()}`);
        return [];
    }


    // add redirect to child
    if (children?.length && !vueRoute.redirect) {
        vueRoute.redirect = {
            name: children[0].name
        };
    }

    if (children?.length) {
        const childRoutes = children.flatMap(child => transformElegantRouteToVueRoute(child, layouts, views));

        if(isFirstLevelRoute(route)) {
            vueRoute.children = childRoutes;
        } else {
            vueRoutes.push(...childRoutes);
        }
    }

    vueRoutes.unshift(vueRoute);
    return vueRoutes;
}

export function createBuiltinVueRoutes() {
    return transformElegantRoutesToVueRoutes(customRoutes, layouts, views);
}

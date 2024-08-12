/**
 * 主题状态管理
 */
export default {
    namespaced: true,
    state: {
        darkMode: false,
        /** 'light' | 'dark' | 'auto' */
        themeScheme: 'light',
        /** Whether to recommend color */
        recommendColor: false,
        /** Theme color */
        themeColor: '',
        /** grayscale mode */
        grayscale: false,
        /** Other color */
        otherColor: {
            info: '',
            success: '',
            warning: '',
            error: '',
        },
        /** Whether info color is followed by the primary color */
        isInfoFollowPrimary: false,
        /** Layout */
        layout: {
            /** 'vertical' | 'horizontal' | 'vertical-mix' | 'horizontal-mix' **/
            mode: 'horizontal',
            /** 'wrapper' | 'content'*/
            scrollMode: 'content'
        },
        /** Page */
        page: {
            /** Whether to show the page transition */
            animate: true,
            /** Page animate mode 'fade' | 'fade-slide' | 'fade-bottom' | 'fade-scale' | 'zoom-fade' | 'zoom-out' | 'none */
            animateMode: 'fade',
        },
        /** Header */
        header: {
            /** Header height */
            height: 100,
            /** Header breadcrumb */
            breadcrumb: {
                /** Whether to show the breadcrumb */
                visible: true,
                /** Whether to show the breadcrumb icon */
                showIcon: true,
            }
        },
        /** Tab */
        tab: {
            /** Whether to show the tab */
            visible: true,
            /**
             * Whether to cache the tab
             *
             * If cache, the tabs will get from the local storage when the page is refreshed
             */
            cache: true,
            /** Tab height */
            height: 100,
            /** Tab mode 'button' | 'chrome'  */
            mode: 'chrome',
        },
        /** Fixed header and tab */
        fixedHeaderAndTab: true,
        /** Sider */
        sider: {
            /** Inverted sider */
            inverted: true,
            /** Sider width */
            width: 100,
            /** Collapsed sider width */
            collapsedWidth: 100,
            /** Sider width when the layout is 'vertical-mix' or 'horizontal-mix' */
            mixWidth: 200,
            /** Collapsed sider width when the layout is 'vertical-mix' or 'horizontal-mix' */
            mixCollapsedWidth: 200,
            /** Child menu width when the layout is 'vertical-mix' or 'horizontal-mix' */
            mixChildMenuWidth: 200,
        },
        /** Footer */
        footer: {
            /** Whether to show the footer */
            visible: true,
            /** Whether fixed the footer */
            fixed: false,
            /** Footer height */
            height: 100,
            /** Whether float the footer to the right when the layout is 'horizontal-mix' */
            right: true
        },
    },
    mutations: {
        TOGGLE_THEME_SCHEME(state) {
            const themeSchemes = ['light', 'dark', 'auto'];
            const index = themeSchemes.findIndex(item => item === state.themeScheme);
            const nextIndex = index === themeSchemes.length - 1 ? 0 : index + 1;
            state.themeScheme = themeSchemes[nextIndex];
        }
    },
    actions: {
        toggleThemeScheme({ commit }){
            commit('TOGGLE_THEME_SCHEME');
        }
    }
};

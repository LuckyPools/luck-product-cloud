export const AdminLayoutHeaderConfig = {
    /**
     * Whether header is visible
     *
     * @default true
     */
    headerVisible: {
        type: Boolean
    },
    /**
     * Header class
     *
     * @default ''
     */
    headerClass: {
        type: String
    },
    /**
     * Header height
     *
     * @default 56px
     */
    headerHeight: {
        type: Number
    },
}


export const AdminLayoutTabConfig = {
    /**
     * Whether tab is visible
     *
     * @default true
     */
    tabVisible: {
        type: Boolean
    },
    /**
     * Tab class
     *
     * @default ''
     */
    tabClass: {
        type: String
    },
    /**
     * Tab height
     *
     * @default 48px
     */
    tabHeight: {
        type: Number
    },
}

/** Sider config */
export const AdminLayoutSiderConfig = {
    /**
     * Whether sider is visible
     *
     * @default true
     */
    siderVisible: {
        type: Boolean
    },
    /**
     * Sider class
     *
     * @default ''
     */
    siderClass: {
        type: String
    },
    /**
     * Mobile sider class
     *
     * @default ''
     */
    mobileSiderClass: {
        type: String
    },
    /**
     * Sider collapse status
     *
     * @default false
     */
    siderCollapse: {
        type: Boolean
    },
    /**
     * Sider width when collapse is false
     *
     * @default '220px'
     */
    siderWidth: {
        type: Number
    },
    /**
     * Sider width when collapse is true
     *
     * @default '64px'
     */
    siderCollapsedWidth: {
        type: Number
    },
}

export const AdminLayoutContentConfig = {
    /**
     * Content class
     *
     * @default ''
     */
    contentClass: {
        type: String
    },
    /**
     * Whether content is full the page
     *
     * If true, other elements will be hidden by `display: none`
     */
    fullContent: {
        type: Boolean
    },
}

/** Footer config */
export const AdminLayoutFooterConfig = {
    /**
     * Whether footer is visible
     *
     * @default true
     */
    footerVisible: {
        type: Boolean
    },
    /**
     * Whether footer is fixed
     *
     * @default true
     */
    fixedFooter: {
        type: Boolean
    },
    /**
     * Footer class
     *
     * @default ''
     */
    footerClass: {
        type: String
    },
    /**
     * Footer height
     *
     * @default 48px
     */
    footerHeight: {
        type: Number
    },
    /**
     * Whether footer is on the right side
     *
     * When the layout is vertical, the footer is on the right side
     */
    rightFooter: {
        type: Boolean
    },
}

export const AdminLayoutProps = {

    ...AdminLayoutHeaderConfig,
    ...AdminLayoutTabConfig,
    ...AdminLayoutSiderConfig,
    ...AdminLayoutContentConfig,
    ...AdminLayoutFooterConfig,

    /**
     * Layout mode
     *
     * - {@link LayoutMode}
     */
    mode: {
        type: String
    },
    /** Is mobile layout */
    isMobile: {
        type: Boolean
    },
    /**
     * Scroll mode
     *
     * - {@link ScrollMode}
     */
    scrollMode: {
        type: String
    },
    /**
     * The id of the scroll element of the layout
     *
     * It can be used to get the corresponding Dom and scroll it
     *
     * @example
     *   use the default id by import
     *   ```ts
     *   import { adminLayoutScrollElId } from '@sa/vue-materials'},
     *   ```
     *
     * @default
     * ```ts
     * const adminLayoutScrollElId = '__ADMIN_LAYOUT_SCROLL_EL_ID__'
     * ```
     */
    scrollElId: {
        type: String
    },
    /** The class of the scroll element */
    scrollElClass: {
        type: String
    },
    /** The class of the scroll wrapper element */
    scrollWrapperClass: {
        type: String
    },
    /**
     * The common class of the layout
     *
     * Is can be used to configure the transition animation
     *
     * @default 'transition-all-300'
     */
    commonClass: {
        type: String
    },
    /**
     * Whether fix the header and tab
     *
     * @default true
     */
    fixedTop: {
        type: Boolean
    },
    /**
     * The max z-index of the layout
     *
     * The z-index of Header,Tab,Sider and Footer will not exceed this value
     */
    maxZIndex: {
        type: Number
    },
}

export const PageTabProps = {
    /** Whether is dark mode */
    darkMode: {
        type: Boolean
    },
    /**
     * The mode of the tab
     * export type PageTabMode = 'button' | 'chrome';
     * - {@link TabMode}
     */
    mode: {
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
    /** The class of the button tab */
    buttonClass: {
        type: String
    },
    /** The class of the chrome tab */
    chromeClass: {
        type: String
    },
    /** Whether the tab is active */
    active: {
        type: Boolean
    },
    /** The color of the active tab */
    activeColor: {
        type: String
    },
    /**
     * Whether the tab is closable
     *
     * Show the close icon when true
     */
    closable: {
        type: Boolean
    }

}

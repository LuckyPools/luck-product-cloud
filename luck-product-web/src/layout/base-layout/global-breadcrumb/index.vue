<template>
    <ABreadcrumb v-if="theme.header.breadcrumb.visible" v-bind="$props" v-on="$listeners">
        <ABreadcrumbItem v-for="item in route.breadcrumbs" :key="item.key">
            <div class="i-flex-y-center align-middle">
                <component :is="item.icon" v-if="theme.header.breadcrumb.showIcon" class="mr-4px text-icon"/>
                {{ item.label }}
            </div>
            <template v-if="item.children?.length" #overlay>
                <AMenu>
                    <AMenuItem v-for="option in item.children" :key="option.key"
                               @click="handleClickMenu(option.routeKey)">
                        <div class="i-flex-y-center align-middle">
                            <component :is="option.icon" v-if="theme.header.breadcrumb.showIcon"
                                       class="mr-4px text-icon"/>
                            {{ option.label }}
                        </div>
                    </AMenuItem>
                </AMenu>
            </template>
        </ABreadcrumbItem>
    </ABreadcrumb>
</template>
<script>
import {mapGetters} from "vuex";
export default {
    name: 'GlobalBreadcrumb',
    inheritAttrs: false,
    data() {
        return {
        }
    },
    computed: {
        ...mapGetters(['route', 'theme']),
    },
    methods: {
        handleClickMenu(key) {
            // todo 切换路由 routerPushByKey(key)
            this.$router.push({name: key, query: {}})
        }
    }
}
</script>
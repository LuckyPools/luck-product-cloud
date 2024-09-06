const proxy = require('http-proxy-middleware');
const unocss = require('@unocss/webpack').default;

module.exports = {
    runtimeCompiler: true,
    devServer: {
        port: 9099,
        proxy: {
            '/api': {
                target: 'http://localhost:9090',
                pathRewrite: { '^/api': '' },
                ws: true,
                changeOrigin: true
            }
        }
    },
    configureWebpack: {
        plugins: [unocss()],
        optimization: {
            realContentHash: true,
        },
    },
    css: {
        extract: true,
        loaderOptions: {
            scss: {
                additionalData: `@import "~@/styles/scss/variables.scss";`,
            },
        },
    }
}


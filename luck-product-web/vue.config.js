const proxy = require('http-proxy-middleware');
const unocss = require('@unocss/webpack').default;

module.exports = {
    devServer: {
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
    }
}


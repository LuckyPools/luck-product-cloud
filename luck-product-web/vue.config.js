const proxy = require('http-proxy-middleware');

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
    }
}

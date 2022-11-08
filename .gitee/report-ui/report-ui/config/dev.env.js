'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"http://127.0.0.1:9095/back/"'
  // BASE_API: '"http://101.35.226.248:9095/back/"'
})

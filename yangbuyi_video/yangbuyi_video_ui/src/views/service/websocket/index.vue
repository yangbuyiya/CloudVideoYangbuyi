<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div class="webSocket">
    <button id="send" class="btn btn-default" @click="sendMsg('发送杨不易')">Send</button>
    <div v-for="item in msgData" :key="item">
      {{ item }}
    </div>
  </div>
</template>
<script>

export default {
  name: 'WebSocket',
  data() {
    return {
      // 消息
      msgData: [],
      websocket: null
    }
  },
  mounted() {
    this.connection()
    // this.initWebSocket()
  },
  destroyed() {
    if (this.websocket != null) this.websocket.close() // 离开路由之后断开websocket连接
  },
  methods: {
    initWebSocket() {
      this.connection()
      // const that = this
      // // 断开重连机制,尝试发送消息,捕获异常发生时重连
      // this.timer = setInterval(() => {
      //   try {
      //     that.websocket.send('hello')
      //   } catch (err) {
      //     console.log('断线了: ' + err)
      //     that.connection()
      //   }
      // }, 5000)
    },

    /**
     * 连接后台ws
     */
    connection() {
      const socketUrl = process.env.VUE_APP_BASE_WS_API + this.$store.getters.name
      if (typeof (WebSocket) === 'undefined') {
        console.log('您的浏览器不支持WebSocket')
        this.$message.error('您的浏览器不支持WebSocket,无法使用推送功能!')
      } else {
        this.websocket = new WebSocket(socketUrl)
        console.log(this.websocket)
        this.websocket.onopen = this.websocketOnopen // 连接成功
        this.websocket.onmessage = this.websocketOnmessage // 广播成功
        this.websocket.onerror = this.websocketOnerror // 连接断开，失败
        this.websocket.onclose = this.websocketClose // 连接关闭
      }
    },
    websocketOnopen() {
      this.sendMsg('连接成功第一次')
      console.log('连接成功')
    },
    websocketOnerror() {
      console.log('连接失败')
    },
    websocketClose() {
      console.log('断开连接')
    },
    websocketOnmessage(data) {
      this.msgData.push(data)
    },
    sendMsg(val) {
      this.websocket.send(val)
    }
  }
}
</script>

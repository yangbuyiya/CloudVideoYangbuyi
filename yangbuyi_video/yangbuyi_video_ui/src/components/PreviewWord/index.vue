<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div>
    <input id="document" type="file">
    <div v-html="vHtml" />
  </div>
</template>
<script>
import mammoth from 'mammoth'
export default {
  data() {
    return {
      vHtml: ''
    }
  },
  mounted() {
    document.getElementById('document').addEventListener('change', this.uploading, false)
  },
  methods: {
    view() {
      mammoth.convertToHtml({ path: 'path/to/document.docx' })
        .then(function(result) {
          // var html = result.value // The generated HTML
          // var messages = result.messages // Any messages, such as warnings during conversion
        })
        .done()
    },
    uploading(event) {
      const that = this
      var file = event.target.files[0] // 获取文件
      var reader = new FileReader()
      reader.readAsArrayBuffer(file)
      reader.onload = function(e) {
        const buffer = e.target.result // 此时是arraybuffer类型
        mammoth.convertToHtml({ arrayBuffer: buffer }).then((result) => {
          console.log(result)
          that.vHtml = result.value
        }).done()
      }
    }
  }
}
</script>

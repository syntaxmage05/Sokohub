(() => {
  class Adapter {
  receive(message) {
  window.messageHandler.post(JSON.stringify(message))
  }
  }
  window.webBridge.adapter = new Adapter()
})()

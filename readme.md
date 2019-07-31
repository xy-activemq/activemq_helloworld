# activeMQ第一个实例
## 犯错：写成了true就无法正常运行
Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
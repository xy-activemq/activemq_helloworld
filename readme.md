# activeMQ第一个实例
## 1、队列
### 犯错：写成了true就无法正常运行
Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

## 2、主题

## 3、持久化
### 1、默认为持久化，但是QUEUE与TOPIC持久化代码操作不同
### 2、QUEQUE在生产端发送消息之后，如果activemq宕机，会进行持久化，等activemq恢复之后，消费端也会得到数据


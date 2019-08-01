# activeMQ第一个实例
## 1、队列QUEUE
### 1、事务、签收关系
**犯错：生产者事务写成了true就无法正常运行**对应（偏向生产者，偏向消费者）
Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
**如果设置为true需要设置session.commit()**


## 2、主题TOPIC

## 3、持久化
### 1、默认为持久化，但是QUEUE与TOPIC持久化代码操作不同
### 2、QUEQUE在生产端发送消息之后，如果activemq宕机，会进行持久化，等activemq恢复之后，消费端也会得到数据


JobResource:
1.createJob:publisher and consumer.
2.updateJob:
3.deleteJob


















POST→JobController→JobService→Save Job →Publish JobCreatedEvent →RabbitMQ →ActivityConsumer→Save Activity
# reproducer-index

Trying to generate picocli commands dynamically using Gizmo in the quarkus-generate-picocli-commands but I cannot index it

Error:

```
[ERROR]   GeneratePicocliCommandsTest » Runtime java.lang.RuntimeException: io.quarkus.builder.BuildException: Build failure: Build failed due to errors
	[error]: Build step io.quarkus.deployment.steps.CombinedIndexBuildStep#build threw an exception: java.lang.IllegalStateException: Failed to index: org/acme/ExampleTopCommand
	at io.quarkus.deployment.index.IndexingUtil.indexClass(IndexingUtil.java:157)
	at io.quarkus.deployment.steps.CombinedIndexBuildStep.build(CombinedIndexBuildStep.java:47)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at io.quarkus.deployment.ExtensionLoader$3.execute(ExtensionLoader.java:909)
	at io.quarkus.builder.BuildContext.run(BuildContext.java:282)
	at org.jboss.threads.ContextHandler$1.runWith(ContextHandler.java:18)
	at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2513)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1538)
	at java.base/java.lang.Thread.run(Thread.java:1589)
	at org.jboss.threads.JBossThread.run(JBossThread.java:501)
Caused by: java.lang.IllegalArgumentException: stream cannot be null
	at org.jboss.jandex.Indexer.indexWithSummary(Indexer.java:2339)
	at io.quarkus.deployment.index.IndexingUtil.indexClass(IndexingUtil.java:152)
	... 10 more
```


```shell script
./mvnw clean package
java -jar target/quarkus-app/quarkus-run.jar "myname"
```
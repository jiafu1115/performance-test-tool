# performance-test-tool
common performance test tool

```
  Usage: performance test tool [options]
  Options:
    --help

  * --record, -r
      record test result class, such as 
      com.test.performance.demo.DemoCollectMethodImpl 
  * --test, -t
      test case class, such as com.test.performance.demo.DemoTestCaseImpl
    -d
      dynamic parameters
      Syntax: -dkey=value
      Default: {}
    -duration
      keep how much time in second for test
      Default: 10
    -thread
      if thread number is set, tps setting is ignored
      Default: 1
    -tps
      if tps is set, thread number setting is ignored
      Default: -1
```

```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="--help"
```
 
```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-t com.test.performance.demo.DemoTestCaseImpl"
```
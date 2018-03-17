# performance-test-tool
common performance test tool

```
Usage: performance test tool [options]
  Options:
  * --test, -t
      test case class, such as com.test.performance.demo.DemoTestCaseImpl
    --record, -r
      record test result class, such as 
      com.test.performance.result.DefaultCollectMethodImpl 
      Default: com.test.performance.result.DefaultCollectMethodImpl
    -D
      dynamic parameters
      Syntax: -Dkey=value
      Default: {}
    -duration
      keep how much time in second for test
      Default: 10
    -help

    -runid
      run id for this test, default is date
      Default: Sat Mar 17 16:11:00 CST 2018
    -thread

      Default: 1
    -tps
      if tps is not set, not limit tps. only loop to execute
      Default: -1
```

```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-help"
```
## Usage: no limit TPS, just use N thread to loop execute

```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-t com.test.performance.demo.DemoTestCaseImpl -thread 5 -duration 20"
```
## Usage: try to reach limit TPS by N thread
```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-t com.test.performance.demo.DemoTestCaseImpl -thread 5 -duration 20  -tps 30"
```

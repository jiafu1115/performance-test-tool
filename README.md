# performance-test-tool
common performance test tool

```
Usage: performance test tool [options]
  Options:
    --record, -r
      record test result class, such as 
      com.test.performance.result.impl.DefaultCollectMethodImpl
      Default: com.test.performance.result.impl.DefaultCollectMethodImpl
  * --test, -t
      test case class, such as com.test.performance.demo.DemoTestCaseImpl
    -D
      dynamic parameters
      Syntax: -Dkey=value
      Default: {}
    -duration
      keep how much time in second for test
      Default: 10
    -help

    -program

    -reportinterval
      interval in seconds to report progress
      Default: 5
    -runid
      run id for this test, default is date
      Default: Sun Mar 18 18:59:13 CST 2018
    -thread

      Default: -1
    -tps

      Default: -1
```

### Usage: help
```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-help"
```

## Usage: test with 1 thread and 1 TPS

```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-t com.test.performance.demo.DemoTestCaseImpl -duration 20"
```

## Usage: no limit TPS, just use N thread to loop execute

```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-t com.test.performance.demo.DemoTestCaseImpl -duration 20 -thread 5"
```

## Usage: try to reach limit TPS by unlimited thread
```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-t com.test.performance.demo.DemoTestCaseImpl -duration 20 -tps 30"


## Usage: try to reach limit TPS by N thread
```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-t com.test.performance.demo.DemoTestCaseImpl -duration 20 -thread 5 -tps 30"
```




$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("example.feature");
formatter.feature({
  "line": 2,
  "name": "To Test Cucumber",
  "description": "",
  "id": "to-test-cucumber",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Cucu"
    }
  ]
});
formatter.before({
  "duration": 6995191703,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "To Start Cucumber",
  "description": "",
  "id": "to-test-cucumber;to-start-cucumber",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@OpenCu"
    }
  ]
});
formatter.step({
  "line": 7,
  "name": "I Start Step",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I Start Step1",
  "keyword": "Then "
});
formatter.match({
  "location": "Example.i_start_step()"
});
formatter.result({
  "duration": 4355579319,
  "status": "passed"
});
formatter.match({
  "location": "Example.i_start_step1()"
});
formatter.result({
  "duration": 148579,
  "status": "passed"
});
formatter.after({
  "duration": 323568324,
  "status": "passed"
});
});
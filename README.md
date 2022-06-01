# ideals-test-automation-project

[![CodeQL](https://github.com/lion17s/ideals-test-automation-project/actions/workflows/codeql-analysis.yml/badge.svg?branch=main)](https://github.com/lion17s/ideals-test-automation-project/actions/workflows/codeql-analysis.yml)
[![Compile Check](https://github.com/lion17s/ideals-test-automation-project/actions/workflows/compile-check.yml/badge.svg?branch=main)](https://github.com/lion17s/ideals-test-automation-project/actions/workflows/compile-check.yml)
[![Linux Browsers Test](https://github.com/lion17s/ideals-test-automation-project/actions/workflows/linux-browsers-test.yml/badge.svg?branch=main)](https://github.com/lion17s/ideals-test-automation-project/actions/workflows/linux-browsers-test.yml)

# Launch tests in GitHub Actions
- Simply make dummy change into main branch: tests will be run using GitHub Actions simultaneously using different browsers(Chrome and Firefox) on Ubuntu OS
- Download [Allure Report Artifact](https://github.com/lion17s/ideals-test-automation-project/actions/workflows/linux-browsers-test.yml) from the latest run. `allure-report` folder should be downloaded
- Open Allure Report locally:
  - Download [Allure CLI](https://github.com/allure-framework/allure2/releases/download/2.18.1/allure-2.18.1.zip)
  - Use `allure` or `allure.bat` depending on OS to execute command `allure open /path/to/allure-report`. Report will be automatically opened in default browser

# Launch tests locally
- Clone the project
- Make sure Java11 installed and `JAVA_HOME` env variable is set properly, Chrome and Firefox browsers are installed and updated
- Navigate into the root of the project
- Execute command:
  - Windows OS: `./gradlew.bat clean test -Dsuites="testng.xml" --info` - tests will be launched in headless mode simultaneously using different browsers(Chrome and Firefox). Wait until test run will be finished. Generate report: `./gradlew.bat downloadAllure` then `./gradlew.bat allureServe` - report will be opened in default browser automatically
  - Mac OS/Linux: `./gradlew clean test -Dsuites=testng.xml --info` - tests will be launched in headless mode simultaneously using different browsers(Chrome and Firefox). Wait until test run will be finished. Generate report: `./gradlew downloadAllure` then `./gradlew allureServe` - report will be opened in default browser automatically
  
To see live test execution remove `--headless` option from `environment.conf` file. Then run tests and open report like described in section above. Or use another command to launch tests:
- Mac OS/Linux: `./gradlew clean test -Denv=desktop.chrome -DthreadCount=3 -Dparallel=methods` - tests will be run simultaneously in 3 separate instances of Chrome browser
- Windows OS: `./gradlew.bat clean test -Denv="desktop.chrome" -DthreadCount=3 -Dparallel="methods"` - tests will be run simultaneously in 3 separate instances of Chrome browser

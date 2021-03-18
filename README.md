# Automation Framework Testing
It's a framework based in Cucumber and Selenium, that includes a system report (_Allure_), builded with Maven for front testing.

##  Installation and usage
You can download the project and install the dependences using Maven commands:
~~~bash
mvn dependency:resolve
mvn clean install
~~~

To run the test using console, you have to run the command `mvn test`.

**Notice**: Actually, the project only runs in Google Chrome. If you want to try with Firefox or another browser, you have to add the corresponding driver and change default browser in file `test.properties`

## Running with IDE: IntellijIDEA
I recommend to install this plugins to run test cases in feature files with IntellijIDEA:
- Cucumber for Java
- Cucumber Scenarios Indexer
- Gherkin

Further, you have to configure your Scenario project (_Main class_) like this:
![Scenario configuration](https://i.imgur.com/8Pn77MV.png)

##  How to use/add new tests
### Adding Tags, IDs and Xpath
All tags must me added in a JSON file in **Pages** folder. For example:
> I want to make tests in Amazon page. Then, I make a JSON file (amazon.json) with 
> tags, xpath, classnames extracted from Amazon DOM page.  

Then, you have to add the JSON file in your tests from feature file. For example:
~~~gherkin
Then I load the DOM Information amazon.json
~~~

## Adding Steps for project
If you need more and specific steps for your tests, you can edit the file **StepDefinitions.java**.

##  Notice
Actually, I'm working in this framework, adding features and solving some bugs. 




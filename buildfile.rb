VERSION_NUMBER = "1.0.0-SNAPSHOT"
GROUP = "garjust"

repositories.remote << "http://repo1.maven.org/maven2"

MOCKITO = 'org.mockito:mockito-all:jar:1.9.0'
HAMCREST = 'org.hamcrest:hamcrest-all:jar:1.1'
LOG4J = 'log4j:log4j:jar:1.2.17'
JUNIT = 'junit:junit:jar:4.10'

desc "The Jag2d project"
define 'jag2d' do
  project.version = VERSION_NUMBER
  project.group = GROUP
  compile.with LOG4J
  test.with MOCKITO, HAMCREST, JUNIT

  package :jar
end

require 'dependencies'

VERSION_NUMBER = "1.1.0-SNAPSHOT"
GROUP = "garjust"

desc "The Jag2d project"
define 'jag2d' do
  project.version = VERSION_NUMBER
  project.group = GROUP

  compile.with DEPENDENCIES
  test.with TEST_DEPENDENCIES

  package :jar
end

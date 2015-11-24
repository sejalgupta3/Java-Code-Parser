@echo off
set cpath=%1
set fileName=%2
java -cp . Parser %cpath%
java -cp . ClubAssociation
cd JavaClass
setlocal disableDelayedExpansion
set "files="
for /r %%g in (*.java) do call set files=%%files%% "%%~nxg"
echo %files%
javadoc -docletpath "%UML_PARSER%" -doclet org.umlgraph.doclet.UmlGraph -private %files%
dot -Tpng -o%cpath%/%fileName%.png graph.dot
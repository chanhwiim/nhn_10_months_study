class 파일을 다른 폴더에 옮기기. javac ./src/com/nhnacademy/hello/*.java -d ./out

jar 파일 만드는 코드 jar --file hello.jar --main-class com.nhnacademy.hello.main -c ./com

***

~/문서/nhnacademy/hello$ jar --file hello.jar --main-class com.nhnacademy.hello.main -c ./com
./com: 해당 파일 또는 디렉토리가 없습니다.

이 에러가 발생한 이유?
=> hello에는 .vscode, lib, out, src, mdFile만 있었다. ./com으로 하면 어디에도 없기 때문에 위의 에러가 발생했다.

해결하기 위해서는?
=> /com이 있는 곳으로 가면 된다. out으로 가거나, src로 가거나. 내가 jar파일을 만들어 놓은 위치는 out이기 때문에 out 디렉토리로 이동해서,
    ./com을 하면 위치를 찾을 수 있게 되고, 에러가 발생하지 않게 된다.


***

javac ./src/com/nhnacademy/hello/*.java -d ./out/

=> 에러 발생함. 왜?

Calculator를 잡지 못해서. 위쪽의 hello.jar 생성, rm hello.jar, 컴파일 부분과는 관련이 없다.
잡지 못한 이유? 못찾아서. Class Path를 안잡아줘서.

=> 해야 할 것. Calculator의 Class Path을 연결시킨 후, 다시 구동하면 돌아간다.

***


수업에서 사용된 코드 : java -cp "./hello.jar:./calculator.jar" com.nhnacademy.hello.Main
내가 쓴 것 : java -cp "./hello.jar:../lib/Calculator.jar" com.nhnacademy.hello.TestCalc

차이가 있는 이유는? .. 부분 => 이거는? 현재 위치의 상위 디렉토리로. 6번째 줄에서 내 위치는: nhnacademy@nhnacademy-Inspiron-14-5420:~/문서/nhnacademy/hello/out

파일 구조는 HELLO 밑에 .vscode, lib, out, src, terminal.txt 즉 out 디렉토리의 상위에 lib가 존재한다.

즉 ./으로 현재 디렉토리에서 이동하는 것이 아닌, ../으로 상위 디렉토리로 이동해서 가야 한다.

nhnacademy@nhnacademy-Inspiron-14-5420:~/문서/nhnacademy/hello$ javac ./src/com/nhnacademy/hello/TestCalc.java -d ./out/ --class-path ./lib/Calculator.jar => class-path 추가.

==> 이걸 한 이유는?

Terminal or 명령 프롬포트를 사용할 경우에는 이런 식으로 lib에 있는 jar 파일의 Path를 직접 잡아줘야 한다.
Tool을 사용할 경우(vsCode, InteliJ 등등), Class Path를 어느 정도는 자동으로 잡아줘서 할 일이 없어서 이런 식으로 동작하는 것을 잘 모르기 때문에 알려주기 위해서.
 
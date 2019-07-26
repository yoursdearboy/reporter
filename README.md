# Reporter

Create reports using JVM Scripting (JSR 223) and Apache POI.

See [test.groovy](https://github.com/yoursdearboy/reporter/blob/master/src/main/resources/reports/test.groovy) for demo.

By default creates PDFs using LibreOffice.

Run with Docker:

```
docker run --rm --name reporter -p 8080:8080 -v /path/to/reports:/reports reporter
```

Demo usage:

```
curl -XPOST http://127.0.0.1:8080/reports/test.groovy -o test.pdf
curl -XPOST -H "Accept: application/vnd.openxmlformats-officedocument.wordprocessingml.document" http://127.0.0.1:8080/reports/test.groovy -o test.docx
```

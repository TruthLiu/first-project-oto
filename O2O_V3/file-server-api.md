```xml
<!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->
<dependency>
  <groupId>org.apache.httpcomponents</groupId>
  <artifactId>httpclient</artifactId>
  <version>4.5.2</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpmime -->
<dependency>
  <groupId>org.apache.httpcomponents</groupId>
  <artifactId>httpmime</artifactId>
  <version>4.5.2</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpcore -->
<dependency>
  <groupId>org.apache.httpcomponents</groupId>
  <artifactId>httpcore</artifactId>
  <version>4.4.6</version>
</dependency>
```

```java
CloseableHttpClient httpClient = HttpClients.createDefault();
HttpPost uploadFile = new HttpPost("http://10.222.29.181:8082/O2OFileServer/upload");
MultipartEntityBuilder builder = MultipartEntityBuilder.create();

builder.addTextBody(
  "storePath",
  "images/test/another1.png",   // store location of 1st file
  ContentType.TEXT_PLAIN
);
builder.addTextBody(
  "storePath",
  "images/test/another2.png",   // store location of 2nd file
  ContentType.TEXT_PLAIN
);
// add more as you like

for (MultipartFile file : files) {
  builder.addBinaryBody(
      "file",
      file.getInputStream(),
      ContentType.APPLICATION_OCTET_STREAM,
      "test.png"   // name as you like! Since file name has already declared in "storePath"
  );
  // remember to add files according to the sequence you add their "storePath"s
}

HttpEntity multipart = builder.build();
uploadFile.setEntity(multipart);
CloseableHttpResponse response = httpClient.execute(uploadFile);
HttpEntity responseEntity = response.getEntity();
```
<html>
<head>
  <title>Welcome!</title>
</head>
<body>
  <h1>
    Welcome ${user}<#if user == "Big Joe">, ${message}</#if>!
  </h1>
  <p>Our latest animal: ${animals?last.kind}</p>
  <hr/>
  <table>
    <thead>
        <tr>
            <th colspan="2">Our Animals</th>
        </tr>
    </thead>
    <tbody>
      <#list animals as animal>
          <tr>
              <td>${animal.kind}</td>
              <td>${animal.size}</td>
              <td>$ ${animal.price}</td>
          </tr>
      </#list>
    </tbody>
</table>
  
</body>
</html>

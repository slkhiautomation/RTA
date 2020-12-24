Feature: Sample Karate SOAP feature

  Background:
    * configure ssl = true
    * url 'https://etisalatdev.systemsltd.local:9443/teamworks/webservices/E2E/E2EOrderTrackingWS.tws?WSDL'
    * def getDate =
  """
  function() {
    return java.lang.System.currentTimeMillis() + ''
  }
  """
    * def getDate2 =
  """
  function() {
    var SimpleDateFormat = Java.type('java.text.SimpleDateFormat');
    var sdf = new SimpleDateFormat('dd-MM-yyyy HH:mm:ss');
    var date = new java.util.Date();
    return sdf.format(date);
  }
  """
  Scenario: OrderLine Item Created SOAP
    * def transactionId = getDate()
    * def requestedDate = getDate2()
    * def TicketRequest = read('Ticket_Creation.xml')
    * print transactionId
#    Given request TicketRequest
    Given request
    """
    <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:e2e="http://E2E/E2EOrderTrackingWS.tws">
   <soap:Header/>
   <soap:Body>
      <e2e:NotifyOrderMilestones>
          <e2e:request>
            <e2e:transactionID>#("TXN-" + transactionId + "12345")</e2e:transactionID>
            <e2e:requestedSystem>BCRM</e2e:requestedSystem>
            <!--Optional:-->
            <e2e:requestedDate>#(requestedDate)</e2e:requestedDate>
            <e2e:E2ERequest>
               <e2e:eventType>OrderLineItemCreated</e2e:eventType>
               <!--Optional:-->
               <!--Optional:-->
               <e2e:BCRMOrderID>#("ORD-" + transactionId + "-123-BCRM")</e2e:BCRMOrderID>
               <e2e:eventDateTime>#(requestedDate)</e2e:eventDateTime>
               <e2e:productIdentifier>Fixed</e2e:productIdentifier>
               <e2e:orderChannel>BCRM</e2e:orderChannel>
               <e2e:customer>
                  <!--Optional:-->
                  <e2e:customerID>B1867709</e2e:customerID>
                  <!--Optional:-->
                  <e2e:partyID>21375976</e2e:partyID>
                  <!--Optional:-->
                  <e2e:customerName>QA Waqar</e2e:customerName>
                  <!--Optional:-->
                  <e2e:partyName>QA TestCustomer Prospect</e2e:partyName>
                  <e2e:customerSegment>SMB</e2e:customerSegment>
                  <!--Optional:-->
                  <e2e:contactName>Test Customer</e2e:contactName>
                  <e2e:customerRegion>DX</e2e:customerRegion>
                  <!--Optional:-->
                  <e2e:owner>Sales</e2e:owner>
               </e2e:customer>
               <!--Optional:-->
               <!--Optional:-->
               <e2e:order>
                  <e2e:ownerUserID>DUSER_18454 Zawaydeh</e2e:ownerUserID>
                  <e2e:businessUnit>ES Account Managers</e2e:businessUnit>
                  <e2e:orderDescription>OrderLineItem Id already exists error should be shown rather than RequestCreation stage was not expected on Request Creation stage OrderLineItem Id already exists error should be shown rather than RequestCreation stage was not expected on Request Creation stage</e2e:orderDescription>
               </e2e:order>
               <!--Optional:-->
               <e2e:orderLineItem>
                  <!--Zero or more repetitions:-->
                  <e2e:item>
                     <!--Optional:-->
                     <e2e:orderLineItemID>#("ORD-"+ transactionId + "-WA-01-02")</e2e:orderLineItemID>
                     <!--Optional:-->
                     <e2e:orderLineItemStatus>Draft</e2e:orderLineItemStatus>
                     <!--Optional:-->
                     <e2e:channelID>Direct Sales</e2e:channelID>
                     <!--Optional:-->
                     <e2e:ownerUserID>DUSER_18454 Zawaydeh</e2e:ownerUserID>
                     <!--Optional:-->
                     <e2e:productOfferingName><![CDATA[Email - FW: [WARNING: UNSCANNABLE EXTRACTION FAILED]RE: طلب توفير خط بيانات TKT-20190527-00226 - Ord qewrqwrqwerqwerqweqwereqwrqwerqwerer]]></e2e:productOfferingName>
                     <!--Optional:-->
                     <e2e:product>
                        <!--Zero or more repetitions:-->
                        <e2e:item>
                           <!--Optional:-->
                           <e2e:productCode>Business Talk</e2e:productCode>
                           <!--Optional:-->
                           <e2e:productFlavour>ProdFlav</e2e:productFlavour>
                           <e2e:productDescription>Product Structure Item</e2e:productDescription>
                        </e2e:item>
                     </e2e:product>
                  </e2e:item>
               </e2e:orderLineItem>
            </e2e:E2ERequest>
         </e2e:request>
      </e2e:NotifyOrderMilestones>
   </soap:Body>
</soap:Envelope>
    """
#    And print TicketRequest
    And header Content-Type = 'application/soap+xml'
    When method post
    Then status 200
    And print response
    * match response contains '<statusDescription>Success</statusDescription>'
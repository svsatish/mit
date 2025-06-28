import java.util.Map;

public void fillFormData(Map<String, String> data) {
    logger.info("Filling Checkout form data from map: {}", data.keySet());

    if (data.containsKey("syfPartnerId")) {
        setSyfPartnerId(data.get("syfPartnerId"));
    }
    if (data.containsKey("transAmount")) {
        setTransAmount(data.get("transAmount"));
    }
    if (data.containsKey("promoOverrideFlag")) {
        selectPromoOverrideFlag(data.get("promoOverrideFlag"));
    }
    if (data.containsKey("mppFromAnywhereInitialAmount")) {
        mppFromAnywhereInitialAmountInput.clear();
        mppFromAnywhereInitialAmountInput.sendKeys(data.get("mppFromAnywhereInitialAmount"));
    }
    if (data.containsKey("promotionalTags")) {
        setPromotionalTags(data.get("promotionalTags"));
    }
    if (data.containsKey("productInfo")) {
        setProductInfo(data.get("productInfo"));
    }
    if (data.containsKey("childSyfPartnerId")) {
        childSyfPartnerIdInput.clear();
        childSyfPartnerIdInput.sendKeys(data.get("childSyfPartnerId"));
    }
    if (data.containsKey("partnerCode")) {
        partnerCodeInput.clear();
        partnerCodeInput.sendKeys(data.get("partnerCode"));
    }
    if (data.containsKey("childMerchantNumber")) {
        childMerchantNumberInput.clear();
        childMerchantNumberInput.sendKeys(data.get("childMerchantNumber"));
    }
    if (data.containsKey("offerNumber1")) {
        offerNumber1Input.clear();
        offerNumber1Input.sendKeys(data.get("offerNumber1"));
    }
    if (data.containsKey("clientTransactionId")) {
        clientTransactionIdInput.clear();
        clientTransactionIdInput.sendKeys(data.get("clientTransactionId"));
    }
    if (data.containsKey("siteCode")) {
        siteCodeInput.clear();
        siteCodeInput.sendKeys(data.get("siteCode"));
    }
    if (data.containsKey("browserFriendlyPaymentToken")) {
        browserFriendlyPaymentTokenInput.clear();
        browserFriendlyPaymentTokenInput.sendKeys(data.get("browserFriendlyPaymentToken"));
    }
    if (data.containsKey("authorizationNumber")) {
        authorizationNumberInput.clear();
        authorizationNumberInput.sendKeys(data.get("authorizationNumber"));
    }
    if (data.containsKey("operatorId")) {
        operatorIdInput.clear();
        operatorIdInput.sendKeys(data.get("operatorId"));
    }
}
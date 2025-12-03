public class PPayAdapter implements PPay {
    // Referência ao objeto do "mundo antigo" (Adaptee)
    private MPay mPay;

    public PPayAdapter(MPay mPay) {
        this.mPay = mPay;
    }

    // --- Métodos de Leitura (Getters) ---
    // Aqui fazemos a "tradução" dos dados

    @Override
    public String getCustCardNo() {
        return mPay.getCreditCardNo();
    }

    @Override
    public String getCardOwnerName() {
        return mPay.getCustomerName();
    }

    @Override
    public String getCardExpMonthDate() {
        // O PPay espera "MM/YY" (uma string só), mas o MPay tem mês e ano separados.
        // Precisamos combinar os dois.
        return mPay.getCardExpMonth() + "/" + mPay.getCardExpYear();
    }

    @Override
    public Integer getCVVNo() {
        // O MPay usa Short, mas o PPay espera Integer. Fazemos a conversão.
        Short cvv = mPay.getCardCVVNo();
        return (cvv != null) ? cvv.intValue() : null;
    }

    @Override
    public Double getTotalAmount() {
        return mPay.getAmount();
    }

    // --- Métodos de Escrita (Setters) ---
    // Repassamos as alterações de volta para o objeto original para manter consistência

    @Override
    public void setCustCardNo(String custCardNo) {
        mPay.setCreditCardNo(custCardNo);
    }

    @Override
    public void setCardOwnerName(String cardOwnerName) {
        mPay.setCustomerName(cardOwnerName);
    }

    @Override
    public void setCardExpMonthDate(String cardExpMonthDate) {
        // Lógica inversa: se recebermos "MM/YYYY", teríamos que quebrar a string.
        // Como o exercício foca na leitura para processar pagamento, podemos deixar simples 
        // ou implementar o split se necessário. Vou deixar repassando apenas se o formato bater.
        String[] parts = cardExpMonthDate.split("/");
        if (parts.length == 2) {
            mPay.setCardExpMonth(parts[0]);
            mPay.setCardExpYear(parts[1]);
        }
    }

    @Override
    public void setCVVNo(Integer cVVNo) {
        if (cVVNo != null) {
            mPay.setCardCVVNo(cVVNo.shortValue());
        }
    }

    @Override
    public void setTotalAmount(Double totalAmount) {
        mPay.setAmount(totalAmount);
    }
}
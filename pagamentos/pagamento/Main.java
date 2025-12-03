public class Main {
    public static void main(String[] args) {

        // 1. O sistema legado cria um objeto MPay (MercadoPapo)

        MPay mPay = new MpayImpl();
        mPay.setCreditCardNo("1234-5678-9101-1121");
        mPay.setCustomerName("Raissa Beatriz");
        mPay.setCardExpMonth("10");
        mPay.setCardExpYear("2030");
        mPay.setCardCVVNo((short) 999);
        mPay.setAmount(2500.50);

        System.out.println("--- Objeto MPay original criado ---");

        // 2. Agora precisamos passar isso para o PayPal (PPay).
        // Criamos o adaptador engolindo o objeto mPay.
        PPay adapter = new PPayAdapter(mPay);

        // 3. Chamamos o método de teste que SÓ ACEITA PPay.
        // Se funcionar, o padrão Adapter foi um sucesso.
        System.out.println("\n--- Enviando para o sistema PayPal (via Adapter) ---");
        testPPay(adapter);
    }

    // Método solicitado no enunciado

    public static void testPPay(PPay pp) {
        System.out.println("Dono do Cartão: " + pp.getCardOwnerName());
        System.out.println("Número do Cartão: " + pp.getCustCardNo());
        System.out.println("Data de Expiração: " + pp.getCardExpMonthDate());
        System.out.println("CVV: " + pp.getCVVNo());
        System.out.println("Valor Total: $" + pp.getTotalAmount());
    }
}
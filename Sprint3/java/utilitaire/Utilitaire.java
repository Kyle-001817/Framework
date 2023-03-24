package utilitaire;

public class Utilitaire {

    public String processUrl(String url_input, String ctx) {
        ctx+="/";
        int ctx_ind = url_input.indexOf(ctx);
        String url = url_input.substring(ctx_ind + ctx.length());

        return url;
    }
}

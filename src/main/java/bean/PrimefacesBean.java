package bean;

import org.primefaces.PrimeFaces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "PrimefacesBean")
@RequestScoped
public class PrimefacesBean {
    private String theme = "saga";
    private String prompt;
    /**
     * 刷新页面存储的值不清除
     */
    private static String token;
    private String result;
    /**
     * FIXME add
     * init themes items , and gen get method
     *
     * @link index.xhtml line 46
     */
    private List<String> themes = Arrays.asList("saga", "GREEN", "BLUE", "BLACK");

    //    not used
    private List<String> countries = Arrays.asList("arya", "luna-amber", "luna-blue", "luna-green", "luna-pink", "nova-colored", "nova-dark", "nova-light", "saga", "vela");


    // Getter and setter for prompt
    public String getPrompt() {
        return prompt;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    // Getter and setter for token
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    // Getter and setter for result
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    // getter and setter for theme
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }

    // getter and setter for themes

    public List<String> getThemes() {
        return themes;
    }

    /**
     * not used
     *
     * @return
     */
    public List<String> getCountries() {
        return countries;
    }


    // Methods
    public void generateMethod() {
        result = "Hello, " + prompt + token + "!";
        // call page JavaScript         
        PrimeFaces.current().executeScript("sendRequest('"+prompt+"','" + token + "')");
    }

    /**
     * 用于接收按钮请求,当前功能无需其他处理
     * @Link index.xhtml line 49
     */
    public void useThemeAction() {
        //
        System.out.println("theme: " + theme);
    }
}

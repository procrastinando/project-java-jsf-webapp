package bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "PrimefacesBean")
@RequestScoped
public class PrimefacesBean {
    private String theme = "saga";
    private String prompt;
    private String token;
    private String result;
    private String themes;
    private List<String> countries;

    @PostConstruct
    public void init() {
        // initialize the list of countries
        countries = Arrays.asList("arya", "luna-amber", "luna-blue", "luna-green", "luna-pink", "nova-colored", "nova-dark", "nova-light", "saga", "vela");
    }

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
    public List<String> getCountries() {
        return countries;
    }
    public void setThemes(List<String> themes) {
        this.themes = themes.toString();
    }

    // Methods
    public void generateMethod() {
        result = "Hello, " + prompt + token + "!";
    }
}

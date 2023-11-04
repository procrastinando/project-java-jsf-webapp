package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "LlmBean")
@RequestScoped
public class LlmBean {
    private String prompt;
    private String result = "`result` will appear here";

    // Getter and setter for prompt
    public String getPrompt() {
        return prompt;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    // Getter and setter for result
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    public void actionMethod() {
        result = "Hello, " + prompt + "!";
    }
}

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GitHubAPITest {

    GitHubAPI gitHubAPI = new GitHubAPI();

    @Test
    @DisplayName("GitHub 연동 테스트")
    void GitHub_연동_테스트() {
        String token = "GitHub에서 발급받은 Token";
        try {
            gitHubAPI.connectGitHUb(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
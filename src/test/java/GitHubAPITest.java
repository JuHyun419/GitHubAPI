import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GitHubAPITest {

    GitHubAPI gitHubAPI = new GitHubAPI();

    @Test
    @DisplayName("GitHub 연동 테스트")
    void GitHub_연동_테스트() {
        String token = "1317f08a8c008fb6a5d38a71338c8294c6cf6b6b";
        try {
            gitHubAPI.connectGitHUb(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
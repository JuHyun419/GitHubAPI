import org.kohsuke.github.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitHubAPI {
    private static final String TOKEN = "GitHub에서 발급받은 Token";
    private static final String REPO_NAME = "whiteship/live-study";
    public static Map<String, Double> users = new HashMap<>();


    public static void main(String[] args) {
        try {
            // GitHub 연동
            connectGitHUb(TOKEN);

            // GitHub Repository 가져오기
            GHRepository repo = getRepo();

            // GitHub의 모든 Issue 가져오기 GHIssueState.ALL => 모든 이슈 가져오기
            List<GHIssue> issues = getIssues(repo);

            for (GHIssue issue : issues) {
                List<GHIssueComment> comments = issue.getComments(); // Issue 내 Comment 가져오기
                getIssueComment(comments); // Issue 별 Comment 유저 아이디 : 참여횟수 추가
            }

            // user 별 참여비율 출력
            printUsers();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GitHub 연동
    public static GitHub connectGitHUb(final String token) throws IOException {
        GitHub gitHub = new GitHubBuilder()
                .withOAuthToken(token)
                .build();
        gitHub.checkApiUrlValidity();

        return gitHub;
    }

    // GitHub에서 Repo 가져오기
    public static GHRepository getRepo() throws IOException {
        return connectGitHUb(TOKEN).getRepository(REPO_NAME);
    }

    // Issue 가져오기
    public static List<GHIssue> getIssues(final GHRepository repo) throws IOException {
        return repo.getIssues(GHIssueState.ALL);    // 매개변수 => 모든 이슈 가져오기
    }

    //
    public static void getIssueComment(final List<GHIssueComment> issueComments) throws IOException {
        for (GHIssueComment comment : issueComments) {
            String userId = comment.getUser().getLogin();
            users.put(userId, users.getOrDefault(userId, 0.0) + 1);
        }
    }

    public static void printUsers() {
        for (String user : users.keySet()) {
            double percent = users.get(user) / 18 * 100;
            System.out.printf("%s: %.2f", user, percent);
            System.out.println("%");
        }
    }

}

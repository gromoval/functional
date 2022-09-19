import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OnlineJobSystem {

    public static long calculateNumberOfVacancies(ApplicantRequest request, Collection<JobDescription> jobs) {
        return jobs.stream()
                .filter(n -> n.getCompany().equals(request.getCompany()) && n.getRequiredExperienceYears() >= request.getExperienceYears())
                .count(); // write your code here
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ApplicantRequest request = Optional.of(scanner.nextLine())
                .map(inputLine -> {
                    String[] parts = inputLine.split("\\|");
                    return new ApplicantRequest(parts[0], Integer.parseInt(parts[1]));
                }).get();

        List<JobDescription> jobs = Stream.iterate(1, i -> scanner.hasNextLine(), i -> i + 1)
                .map(i -> scanner.nextLine())
                .map(inputLine -> {
                    String[] parts = inputLine.split("\\|");
                    return new JobDescription(parts[0], parts[1], Integer.parseInt(parts[2]));
                })
                .collect(Collectors.toList());

        System.out.println(calculateNumberOfVacancies(request, jobs));
    }
}

class ApplicantRequest {
    private final String company;
    private final int experienceYears;

    ApplicantRequest(String company, int experienceYears) {
        this.company = company;
        this.experienceYears = experienceYears;
    }

    public String getCompany() {
        return company;
    }

    public int getExperienceYears() {
        return experienceYears;
    }
}

class JobDescription {
    private final String title;
    private final String company;
    private final int requiredExperienceYears;

    JobDescription(String title, String company, int requiredExperienceYears) {
        this.title = title;
        this.company = company;
        this.requiredExperienceYears = requiredExperienceYears;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public int getRequiredExperienceYears() {
        return requiredExperienceYears;
    }
}

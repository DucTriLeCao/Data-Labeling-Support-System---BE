package project.dlss.repository;

public class ProjectRepository {

    public long save(String name, String description) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO projects(name, description) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, name);
            ps.setString(2, description);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void overview(long projectId) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql = """
                SELECT 
                    p.name,
                    COUNT(DISTINCT d.id) AS total_datasets,
                    COUNT(di.id) AS total_items
                FROM projects p
                LEFT JOIN datasets d ON d.project_id = p.id
                LEFT JOIN data_items di ON di.dataset_id = d.id
                WHERE p.id = ?
                GROUP BY p.name
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, projectId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Project: " + rs.getString("name"));
                System.out.println("Total datasets: " + rs.getInt("total_datasets"));
                System.out.println("Total items: " + rs.getInt("total_items"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

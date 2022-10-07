import com.cunningbird.templates.contractfirstgrpc.HelloRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestContext {

    @Test
    public void contextLoads() {
        HelloRequest request = HelloRequest.newBuilder().setFirstName("Ricardo").setLastName("Milos").build();

        assertEquals(request.getFirstName(), "Ricardo");
        assertEquals(request.getLastName(), "Milos");
    }
}

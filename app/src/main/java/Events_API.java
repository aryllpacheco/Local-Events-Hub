public class Events_API {
    Map<String, String> parameter = new HashMap<>();

    parameter.put("engine","google_events");
    parameter.put("q","Events");
    parameter.put("hl","en");
    parameter.put("gl","in");
    parameter.put("google_domain","google.co.in");
    parameter.put("location","India");
    parameter.put("api_key","secret_api_key");

    GoogleSearch search = new GoogleSearch(parameter);

try

    {
        JsonObject results = search.getJson();
    } catch(
    SerpApiSearchException ex)

    {
        System.out.println("Exception:");
        System.out.println(ex.toString());
    }
}

package com.srivn.works.smaster.smasterhome.controls;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UsersControl.class)
class UsersControlTest {

	/*
	 * @Mock UserRepository userRepository;
	 * 
	 * @MockBean UsersService usersService;
	 * 
	 * @InjectMocks UsersControl usersControl;
	 * 
	 * @Autowired private MockMvc mockMvc;
	 * 
	 * private UserInfo sampleDTONew; private UserInfo sampleDTODup;
	 * 
	 * @BeforeEach public void setup() { sampleDTODup =
	 * UserInfo.builder().userName("UName").userEmail("test@srivn.com").userAge(10).
	 * build(); sampleDTONew =
	 * UserInfo.builder().userName("UName02").userEmail("test02@srivn.com").userAge(
	 * 20).build();
	 * 
	 * }
	 * 
	 * @DisplayName("Test Adding a New User")
	 * 
	 * @Test
	 * 
	 * @CrossOrigin
	 * 
	 * @PostMapping(value = "/addNewUser", consumes =
	 * MediaType.APPLICATION_JSON_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE) final void test_AddNewUser_YES() throws
	 * Exception {
	 * when(usersService.addNewUser(sampleDTODup)).thenReturn(SmasterMsg.builder()
	 * .statusCode(HttpStatus.ACCEPTED.value()).message("SUCCESS : User created!").
	 * build()); String url = "/users/addNewUser"; String inputData =
	 * mapToJson(sampleDTONew);
	 * 
	 * MvcResult mvcResult = mockMvc.perform(
	 * MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE
	 * ).content(inputData)) .andReturn();
	 * assertEquals(mvcResult.getResponse().getStatus(), 200); }
	 * 
	 * @DisplayName("Test List of All Users")
	 * 
	 * @Test
	 * 
	 * @CrossOrigin
	 * 
	 * @GetMapping(value = "/getAllUserInfo", produces =
	 * MediaType.APPLICATION_JSON_VALUE) final void test_GetAllUserInfo() throws
	 * Exception { when(usersService.getAllUserInfo()).thenReturn(
	 * SmasterMsg.builder().statusCode(HttpStatus.ACCEPTED.value()).
	 * message("SUCCESS : User created!") .data(Arrays.asList(new UserInfo[] {
	 * sampleDTODup, sampleDTONew })).build()); String url =
	 * "/users/getAllUserInfo"; MvcResult mvcResult =
	 * mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.
	 * APPLICATION_JSON_VALUE)) .andReturn();
	 * assertEquals(mvcResult.getResponse().getStatus(), 200);
	 * 
	 * }
	 * 
	 * @DisplayName("Test Get User by email")
	 * 
	 * @Test
	 * 
	 * @CrossOrigin
	 * 
	 * @GetMapping(value = "/getUserByEmail", produces =
	 * MediaType.APPLICATION_JSON_VALUE) final void test_GetUserByEmail() throws
	 * Exception {
	 * when(usersService.getUserByEmail(sampleDTODup.getUserEmail())).thenReturn(
	 * sampleDTODup); String url = "/users/getUserByEmail"; MvcResult mvcResult =
	 * mockMvc.perform(MockMvcRequestBuilders.get(url) .param("userEmail",
	 * sampleDTODup.getUserEmail()).accept(MediaType.APPLICATION_JSON_VALUE)).
	 * andReturn(); assertEquals(mvcResult.getResponse().getStatus(), 200); }
	 * 
	 * private String mapToJson(Object obj) { ObjectMapper mapper = new
	 * ObjectMapper(); try { return mapper.writeValueAsString(obj); } catch
	 * (JsonProcessingException e) { // TODO Auto-generated catch block return
	 * "ERROR Parsing JSON"; } }
	 */
}

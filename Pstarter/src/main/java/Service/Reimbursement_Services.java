package Service;

import java.util.ArrayList;
import java.util.List;

import com.revature.MockReimbursement;

import Models.Reimbursement_;
import Models.Roles;
import Models.Status;
import Models.Type;
import Models.Users_;
import DAO.reimbursementDAO;

public class Reimbursement_Services {
	//update
	public static void update(Reimbursement_ unprocessedReimbusement, int resolverId, Status updatedStatus) {
		for(Reimbursement_ reimbursement : reimbursements) {
			if(reimbursement.getId() == unprocessedReimbusement.getId()) {
				reimbursement.setResolver(resolverId);
				reimbursement.setStatus(updatedStatus);
				return;
			}
		}
		throw new RuntimeException("There was an error processing this reimbursement, please try again.");
	}
	
	//submit	
	public static void submitReimbursement(Reimbursement_ reimbursementToBeSubmitted) {
		Reimbursement_ latestReimbursement = reimbursements.get(reimbursements.size() - 1);
		//increment id number by 1//
		int id = latestReimbursement.getId() + 1;
		
		reimbursementToBeSubmitted.setId(id);
		reimbursementToBeSubmitted.setStatus(Status.pending);
		reimbursements.add(reimbursementToBeSubmitted);
	}
	
	//resolved
	public static List<Reimbursement_> getResoledReimbursements(){
		List<Reimbursement_> resolvedReimbursements = new ArrayList<>();
		
		for(Reimbursement_ reimbursement : reimbursements) {
			if(reimbursement.getStatus() == Status.approved || reimbursement.getStatus() == Status.denied) {
				resolvedReimbursements.add(reimbursement);
			}
		}
		return resolvedReimbursements;
	}
	
	//pending
	public static List<Reimbursement_> getPendingReimbursements(){
		List<Reimbursement_> pendingReimbursements = new ArrayList<>();>
		
		for(Reimbursement_ reimbursement : reimbursements) {
			if(reimbursement.getStatus() == Status.pending) {
				pendingReimbursements.add(reimbursement);
			}
		}
		return pendingReimbursements;
	}
	
	//id
	public static Reimbursement_ getReimbursementById(int id) {
		for(Reimbursement_ reimbursement : reimbursements) {
			if(reimbursement.getId() == id) {
				return reimbursement;
			}
		}
		return null;
	}
	
	//author
	public static List<Reimbursement_> getReimbursementsByAuthor(int userId){
		List<Reimbursement_> userReimbursements = new ArrayList<>();
		
		for(Reimbursement_ r : reimbursements) {
			if(r.getAuthor() == userId || r.getResolver() == userId) {
				userReimbursements.add(r);
			}
		}
		return userReimbursements;
	}

	public static Users_ getUserById(int userChoice) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//Resolver not manager
	@Test
	public void testUpdateThrowsIllegalArgumentExceptionWhenResolverIsNotManager() {
		when(User_Services.getUserById(anyInt())).thenReturn(Rapmon);
		
		assertThrows(IllegalArgumentException.class,
				() -> reimbursementService.update(Reimbursement_To_Process, Rapmon.getId(), Status.Approved)
		);
		
		verify(reimbursementDAO, never()).update(Reimbursement_To_Process);
		verify(User_Services).getUserById(Rapmon.getId());
	}
	
	//Submitted by manager
	@Test
	public void testSubmitReimbursementThrowsIllegalArgumentExceptionWhenSubmittedByManager() {
		when(User_Services.getUserById(anyInt())).thenReturn(Rapmon);
		
		assertThrows(IllegalArgumentException.class,
				() -> reimbursementService.submitReimbursement(Reimbursement_To_Process, Rapmon.getId(), Status.Approved)
		);
		
		verify(reimbursementDAO, never()).create(Reimbursement_To_Process);
		verify(User_Services).getUserById(Rapmon.getId());
	}
	
	@Test
	public void testReimbursementStatusIsChangedAfterUpdate() {
		when(User_Services.getUserById(anyInt())).thenReturn(Rapmon);
		
		assertEquals(Status.approved, Reimbursement_Services.update(Reimbursement_To_Process, Rapmon.getId(), Status.approved).getStatus());
		
		verify(reimbursementDAO).update(Reimbursement_To_Process);
		verify(User_Services).getUserById(Rapmon.getId());
	}
	
	@Test
	public void testResolverIsAssignedAfterReimbursementUpdate() {
		when(User_Services.getUserById(anyInt())).thenReturn(Rapmon);
		
		assertEquals(Rapmon.getId(), Reimbursement_Services.update(Reimbursement_To_Process, Rapmon.getId(), Status.approved).getResolver());
		
		verify(reimbursementDAO).update(Reimbursement_To_Process);
		verify(User_Services).getUserById(Rapmon.getId());
	}
	
	private static ReimbursementService Reimbursement_Services;
	private static UserService user_Services;
	private static reimbursementDAO reimbursementDAO;
	
	private Reimbursement_ Reimbursement_To_Process;
	private List<Reimbursement_> mockPendingReimbursements;
	private List<Reimbursement_> mockApprovedReimbursements;
	private List<Reimbursement_> mockDeniedReimbursements;
	private Users_ Rapmon;
	private Users_ Suga;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		Reimbursement_Services = new ReimbursementService();
	}
	
	@Before
	public void setUp() throws Exception{
		User_Services = mock(UserService.class);
		reimbursementDAO = mock(DAO.reimbursementDAO.class);
		
		MockReimbursement mockReimbursement = new MockReimbursement();
		
		Reimbursement_Services.reimbursementDAO = reimbursementDAO;
		Reimbursement_Services.User_Services = User_Services;
		Rapmon = new Users_(1, "Rapmon", "password", Roles.employee);
		Suga = new Users_(1, "Suga", "password", Roles.manager);
		Reimbursement_To_Process = new Reimbursement_(2, Rapmon.getId(), "Oracle Certification", Type.other, Status.pending, 150.00);
		
		List<Reimbursement_> mockReimbursements = mockReimbursement.getReimbursement();
		mockPendingReimbursements = new ArrayList<>();
		mockApprovedReimbursements = new ArrayList<>();
		mockDeniedReimbursements = new ArrayList<>();
		
		for(Reimbursement_ reimbursement : mockReimbursements) {
			if(reimbursement.getStatus() == Status.pending) {
				mockPendingReimbursements.add(reimbursement);
			}else if(reimbursement.getStatus() == Status.approved) {
				mockApprovedReimbursements.add(reimbursement);
			}else {
				mockDeniedReimbursements.add(reimbursement);
			}
		}
	}
	
	@Test
	public void testGetResolvedReimbursementsReturnsOnlyApprovedAndDenied() {
		when(reimbursementDAO.getReimbursementsByStatus(Status.approved)).thenReturn(mockApprovedReimbursements);
		when(reimbursementDAO.getReimbursementsByStatus(Status.denied)).thenReturn(mockDeniedReimbursements);
		
		List<Reimbursement_> resolvedReimbursements = new ArrayList<>();
		resolvedReimbursements.addAll(mockApprovedReimbursements);
		resolvedReimbursements.addAll(mockDeniedReimbursements);
		
		assertEquals(resolvedReimbursements, Reimbursement_Services.getResolvedReimbursements());
		
		verify(reimbursementDAO).getReimbursementsByStatus(Status.approved);
		verify(reimbursementDAO).getReimbursementsByStatus(Status.denied);
	}
	
	@Test
	public void testGetPendingReimbursementsReturnsOnlyPending() {
		when(reimbursementDAO.getReimbursementsByStatus(any(Status.class))).thenReturn(mockPendingReimbursements);
		
		assertEquals(mockPendingReimbursements, Reimbursement_Services.getPendingReimbursements());
		
		verify(reimbursementDAO).getReimbursementsByStatus(Status.pending);
	}
}

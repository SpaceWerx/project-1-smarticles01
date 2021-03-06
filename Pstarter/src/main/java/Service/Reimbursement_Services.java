package Service;

//import static org.junit.jupiter.api.Assertions.assertThrows;

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
	public static Reimbursement_ update(Reimbursement_ unprocessedReimbursement, int resolverId, Status updatedStatus) {
		List<Reimbursement_> userReimbursements = new ArrayList<>();
		for(Reimbursement_ reimbursement : userReimbursements) {
			if(reimbursement.getId() == unprocessedReimbursement.getId()) {
				reimbursement.setResolver(resolverId);
				reimbursement.setStatus(updatedStatus);
				return unprocessedReimbursement;
			}
		}
		throw new RuntimeException("There was an error processing this reimbursement, please try again.");
	}
	
	//submit	
	public int submitReimbursement(Reimbursement_ reimbursementToBeSubmitted) {
		reimbursementDAO rDAO = new reimbursementDAO();
		Users_ employee = Reimbursement_Services.getUserById(reimbursementToBeSubmitted.getAuthor());
		


        reimbursementToBeSubmitted.setStatus(Status.pending);


        return rDAO.create(reimbursementToBeSubmitted);
	}
	
	//resolved
	public static List<Reimbursement_> getResolvedReimbursements(){
		List<Reimbursement_> userReimbursements = new ArrayList<>();
		List<Reimbursement_> resolvedReimbursements = new ArrayList<>();
		
		for(Reimbursement_ reimbursement : userReimbursements) {
			if(reimbursement.getStatus() == Status.approved || reimbursement.getStatus() == Status.denied) {
				resolvedReimbursements.add(reimbursement);
			}
		}
		return resolvedReimbursements;
	}
	
	//pending
	public static List<Reimbursement_> getPendingReimbursements(){
		List<Reimbursement_> userReimbursements = new ArrayList<>();
		List<Reimbursement_> pendingReimbursements = new ArrayList<>();
		
		for(Reimbursement_ reimbursement : userReimbursements) {
			if(reimbursement.getStatus() == Status.pending) {
				pendingReimbursements.add(reimbursement);
			}
		}
		return pendingReimbursements;
	}
	
	//id
	public static Reimbursement_ getReimbursementById(int id) {
		List<Reimbursement_> userReimbursements = new ArrayList<>();
		for(Reimbursement_ reimbursement : userReimbursements) {
			if(reimbursement.getId() == id) {
				return reimbursement;
			}
		}
		return null;
	}
	
	//author
	public static List<Reimbursement_> getReimbursementsByAuthor(int userId){
		List<Reimbursement_> userReimbursements = new ArrayList<>();
		
		for(Reimbursement_ r : userReimbursements) {
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
	
	/*
	//Resolver not manager
	@Test
	public void testUpdateThrowsIllegalArgumentExceptionWhenResolverIsNotManager() {
		when(User_Services.getUserById(anyInt())).thenReturn(Rapmon);
		
		assertThrows(IllegalArgumentException.class,
				() -> reimbursementService.update(Reimbursement_To_Process, Rapmon.getId(), Status.Approved)
		);
		
		verify(reimbursementDAO, never()).update(Reimbursement_To_Process);
		verify(user_Services).getUserById(Rapmon.getId());
	}
	
	//Submitted by manager
	@Test
	public void testSubmitReimbursementThrowsIllegalArgumentExceptionWhenSubmittedByManager() {
		when(User_Services.getUserById(anyInt())).thenReturn(Rapmon);
		
		assertThrows(IllegalArgumentException.class,
				() -> reimbursementService.submitReimbursement(Reimbursement_To_Process, Rapmon.getId(), Status.Approved)
		);
		
		verify(reimbursementDAO, never()).create(Reimbursement_To_Process);
		verify(user_Services).getUserById(Rapmon.getId());
	}
	
	@Test
	public void testReimbursementStatusIsChangedAfterUpdate() {
		when(User_Services.getUserById(anyInt())).thenReturn(Rapmon);
		
		assertEquals(Status.approved, Reimbursement_Services.update(Reimbursement_To_Process, Rapmon.getId(), Status.approved).getStatus());
		
		verify(reimbursementDAO).update(Reimbursement_To_Process);
		verify(user_Services).getUserById(Rapmon.getId());
	}
	
	@Test
	public void testResolverIsAssignedAfterReimbursementUpdate() {
		when(User_Services.getUserById(anyInt())).thenReturn(Rapmon);
		
		assertEquals(Rapmon.getId(), Reimbursement_Services.update(Reimbursement_To_Process, Rapmon.getId(), Status.approved).getResolver());
		
		verify(reimbursementDAO).update(Reimbursement_To_Process);
		verify(user_Services).getUserById(Rapmon.getId());
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
		Reimbursement_Services = new Reimbursement_Services();
	}
	
	@Before
	public void setUp() throws Exception{
		user_Services = mock(User_Services.class);
		reimbursementDAO = mock(DAO.reimbursementDAO.class);
		
		MockReimbursement mockReimbursement = new MockReimbursement();
		
		Reimbursement_Services.reimbursementDAO = reimbursementDAO;
		Reimbursement_Services.User_Services = user_Services;
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
	*/
	//handles submission, processing and retrieval of reimbursements//
	public class ReimbursementHandles{
		reimbursementDAO reimbursementDAO = new reimbursementDAO();
		User_Services userService = new User_Services();
		
		//retrieves pending reimbursements//
		public List<Reimbursement_> getPendingReimbursements(){
			
			return reimbursementDAO.getReimbursementsByStatus(Status.pending);
		}
		
		//return combined list of reimbursements status'd as either approved or denied//
		public List<Reimbursement_> getResolvedReimbursements(){
			List<Reimbursement_> resolvedReimbursements = new ArrayList<>();
			
			resolvedReimbursements.addAll(reimbursementDAO.getReimbursementsByStatus(Status.approved));
			resolvedReimbursements.addAll(reimbursementDAO.getReimbursementsByStatus(Status.denied));
			
			return resolvedReimbursements;
		}
		
		//accept new reimbursement submission from employees only and return positive integer
		public int submitReimbursement(Reimbursement_ reimbursementToBeSubmitted) {
			Users_ employee = User_Services.getUserById(reimbursementToBeSubmitted.getAuthor());
			
			if(employee.getRole() != Roles.employee) {
				throw new IllegalArgumentException("Managers can't submit reimbursement requests here.");
			}else {
				reimbursementToBeSubmitted.setStatus(Status.pending);
				
				return reimbursementDAO.create(reimbursementToBeSubmitted);
			}	
		}
	}
	
	//returns updated fields of full reimbursement, manager id, new status, and ensure manager role//
	public Reimbursement_ updateAllFields(Reimbursement_ unprocessedReimbursement, int resolverId, Status updatedStatus) {
		reimbursementDAO rDAO = new reimbursementDAO();
		Users_ manager = User_Services.getUserById(resolverId);
		
		if(manager.getRole() != Roles.manager) {
			throw new IllegalArgumentException("An employee can't process reimbursement requests.");
		}else {
			unprocessedReimbursement.setResolver(resolverId);
			unprocessedReimbursement.setStatus(updatedStatus);
			rDAO.update(unprocessedReimbursement);
			
			return unprocessedReimbursement;
		}
	}
	
	public Reimbursement_ getReimbursementsById(int id) {
		reimbursementDAO rDAO = new reimbursementDAO();
		
		return rDAO.getReimbursementsById(id);
	}
	
}

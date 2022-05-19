package Service;

import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement_;
import Models.Status;
import Models.Users_;

public class Reimbursement_Services {
	//update
	public void update(Reimbursement_ unprocessedReimbusement, int resolverId, Status updatedStatus) {
		for(Reimbursement_ reimbursement : reimbursements) {
			if(reimbursement.getId() == unprocessedReimbursement.getId()) {
				reimbursement.setResolver(resolverId);
				reimbursement.setStatus(updatedStatus);
				return;
			}
		}
		throw new RuntimeException("There was an error processing this reimbursement, please try again.");
	}
	
	//submit	
	public void submitReimbursement(Reimbursement_ reimbursementToBeSubmitted) {
		Reimbursement_ latestReimbursement = reimbursements.get(reimbursements.size() - 1);
		//increment id number by 1//
		int id = latestReimbursement.getId() + 1;
		
		reimbursementToBeSubmitted.setId(id);
		reimbursementToBeSubmitted.setStatus(Status.pending);
		reimbursements.add(reimbursementToBeSubmitted);
	}
	
	//resolved
	public List<Reimbursement_> getResoledReimbursements(){
		List<Reimbursement_> resolvedReimbursements = new ArrayList<>();
		
		for(Reimbursement_ reimbursement : reimbursements) {
			if(reimbursement.getStatus() == Status.approved || reimbursement.getStatus() == Status.denied) {
				resolvedReimbursements.add(reimbursement);
			}
		}
		return resolvedReimbursements;
	}
	
	//pending
	public List<Reimbursement_> getPendingReimbursements(){
		List<Reimbursement_> pendingReimbursements = new ArrayList<>();>
		
		for(Reimbursement_ reimbursement : reimbursements) {
			if(reimbursement.getStatus() == Status.pending) {
				pendingReimbursements.add(reimbursement);
			}
		}
		return pendingReimbursements;
	}
	
	//id
	public Reimbursement_ getReimbursementById(int id) {
		for(Reimbursement_ reimbursement : reimbursements) {
			if(reimbursement.getId() == id) {
				return reimbursement;
			}
		}
		return null;
	}
	
	//author
	public List<Reimbursement_> getReimbursementsByAuthor(int userId){
		List<Reimbursement_> userReimbursements = new ArrayList<>();
		
		for(Reimbursement_ r : reimbursements) {
			if(r.getAuthor() == userId || r.getRevolver() == userId) {
				userReimbursements.add(r);
			}
		}
		return userReimbursements;
	}
}

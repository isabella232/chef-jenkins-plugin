package io.chef.jenkinsci.plugins.chef_automate;

import hudson.Extension;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.workflow.steps.*;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.Collections;
import java.util.Set;

/**
 * Custom Jenkins pipeline step which performs linting for Chef
 * cookbooks using cookstyle from the ChefDK.
 */
public class ChefCookbookCookstyleStep extends ChefCookbookStep {

    @DataBoundConstructor
    public ChefCookbookCookstyleStep() {}

    @Override
    public StepExecution start(StepContext context) throws Exception {

        return new ChefCookbookCookstyleStepExecution(context);
    }

    @Extension
    public static class DescriptorImpl extends StepDescriptor {

        @Override
        public String getFunctionName() {
            return "chef_cookbook_cookstyle";
        }

        @Override
        public String getDisplayName() {
            return "Chef Cookbook Lint (Cookstyle)";
        }

        @Override
        public Set<? extends Class<?>> getRequiredContext() {
            return Collections.singleton(TaskListener.class);
        }
    }

   public static class ChefCookbookCookstyleStepExecution extends ChefExecution  {

      	private static final long serialVersionUID = 1L;

		ChefCookbookCookstyleStepExecution(StepContext context) {
            super(context);
        }

		@Override
		protected String[] getCommands() {
			return new String [] {"chef exec cookstyle . --format progress"};
		}
   }
}
